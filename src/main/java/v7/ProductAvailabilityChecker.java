package v7;

import util.LogPrinter;

@SuppressWarnings("Duplicates")
public class ProductAvailabilityChecker {

    private final ApiRequestHandler apiRequestHandler;

    public ProductAvailabilityChecker(ApiRequestHandler apiRequestHandler) {
        this.apiRequestHandler = apiRequestHandler;
    }

    public String isAvailable(String details) {
        try {
            String[] orderDetails = details.split("\\|");
            if (orderDetails.length != 2) {
                return "Request Object Parameter is invalid.";
            }

            String productId = orderDetails[0];
//        String product = apiRequestHandler.get(String.format("http://172.16.1.71:5080/products/id/%s", productId));
            ProductClient productClient = new ProductClient(apiRequestHandler, productId);
            Thread productClientThread = new Thread(productClient);
            productClientThread.start();

            String[] inventory = apiRequestHandler.get(String.format("http://172.16.1.71:5080/inventory/id/%s", productId)).split(",");
            productClientThread.join();
            String product = productClient.getProduct();
            LogPrinter.logMsg("product "+product);
            if (product.equals("null")) {
                return "Product not available.";
            }

            int availableQty = Integer.parseInt(inventory[1]);
            int orderQty = Integer.parseInt(orderDetails[1]);
            LogPrinter.logMsg(String.format("Available Qty: %d | Ordered Qty: %d", availableQty, orderQty));
            String productName = product.split(",")[2];
            if (availableQty >= orderQty) {
                return productName + " is Available";
            } else {
                return productName + " is NOT Available";
            }
        } catch (Exception e) {
            LogPrinter.logMsg("Exception in Thread join " + e.getMessage());
        }
        return "";
    }

    static class ProductClient implements Runnable {

        private ApiRequestHandler apiRequestHandler;
        private String productId;
        private String product;

        public ProductClient(ApiRequestHandler apiRequestHandler, String productId) {
            this.apiRequestHandler = apiRequestHandler;
            this.productId = productId;
        }

        @Override
        public void run() {
            product = apiRequestHandler.get(String.format("http://172.16.1.71:5080/products/id/%s", productId));
        }

        public String getProduct() {
            return product;
        }
    }
}

package v6;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import util.LogPrinter;

import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;

public class ProductAvailabilityChecker {

    private final ApiRequestHandler apiRequestHandler;

    public ProductAvailabilityChecker(ApiRequestHandler apiRequestHandler) {
        this.apiRequestHandler = apiRequestHandler;
    }

    public String isAvailable(String details) {

        String[] orderDetails = details.split("\\|");
        if (orderDetails.length != 2) {
            return "Request Object Parameter is invalid.";
        }
        String productId = orderDetails[0];
        String product = apiRequestHandler.get(String.format("http://localhost:5080/products/id/%s", productId));

        if (product.equals("null")) {
            return "Product not available.";
        }
        String[] inventory = apiRequestHandler.get(String.format("http://localhost:5080/inventory/id/%s", productId)).split(",");

        int availableQty = Integer.parseInt(inventory[1]);
        int orderQty = Integer.parseInt(orderDetails[1]);
        LogPrinter.logMsg(String.format("Available Qty: %d | Ordered Qty: %d", availableQty, orderQty));
        String productName = product.split(",")[2];
        if (availableQty >= orderQty) {
            return productName +" is Available";
        } else {
            return productName+ " is NOT Available";
        }
    }
}

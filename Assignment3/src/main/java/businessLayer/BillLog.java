package businessLayer;

import model.Bill;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
public class BillLog {
    private static final String LOG_PATH_FILE = "C:\\Users\\Szili\\Desktop\\30222SzollosiSzilard-Arpad\\Assignment3\\src\\main\\factura.log";

    public void generateBill(int id , int idProduct,int idCustomer, int amount) {
        Bill bill = new Bill(id,idProduct,idCustomer,amount);
        String logMessage = bill.toString();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_PATH_FILE, true));
            writer.write(LocalDateTime.now().toString() + " - " + logMessage + "\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

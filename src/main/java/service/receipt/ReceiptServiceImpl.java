package service.receipt;

import entity.Drug;
import entity.Receipt;
import repository.receipt.interfaces.ReceiptRepository;
import service.receipt.interfaces.ReceiptService;

public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;

    public ReceiptServiceImpl(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    @Override
    public void save(Receipt receipt) {
        receiptRepository.save(receipt);
    }

    @Override
    public Receipt load(long id) {
        return receiptRepository.load(id);
    }

    @Override
    public void saveDrug(long id, Drug drug, int number) {
        receiptRepository.saveDrug(id, drug, number);
    }

    @Override
    public Receipt loadPatientReceipt(long id){
        return receiptRepository.loadPatientReceipt(id);
    }

    @Override
    public void removeDrugFromReceipt(long id){
        receiptRepository.removeDrugFromReceipt(id);
    }

    @Override
    public void update(Receipt receipt){
        receiptRepository.update(receipt);
    }

}

package hello.inven.helloinven.service;

import hello.inven.helloinven.model.MyUser;
import hello.inven.helloinven.model.ResponseAjax;

import java.util.List;

public interface ClerkService {
    ResponseAjax findManagerAndEmployee();

    ResponseAjax assignItemSerial(MyUser clerk, Long itemId, List<Long> employeeValues);

    ResponseAjax receiveItemRequest(MyUser clerk);

    ResponseAjax ItemRequestActions(Long actionTransactionId, Long itemId, Boolean action);
    //Accept / Reject Item Request
}

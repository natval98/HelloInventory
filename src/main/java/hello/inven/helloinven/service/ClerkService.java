package hello.inven.helloinven.service;

import hello.inven.helloinven.model.MyUser;
import hello.inven.helloinven.response.ResponseAjax;

import java.util.List;

public interface ClerkService {
    ResponseAjax findManagerAndEmployee();

    ResponseAjax assignItemSerial(MyUser clerk, Long itemId, List<Long> employeeValues);

    ResponseAjax receiveItemRequest(MyUser clerk);

    ResponseAjax itemRequestActions(Long actionTransactionId, Long itemId, Long itemSerial, Boolean action);
    //Accept / Reject Item Request
}

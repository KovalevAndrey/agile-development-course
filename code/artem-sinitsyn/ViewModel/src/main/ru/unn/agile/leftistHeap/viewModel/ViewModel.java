package ru.unn.agile.leftistHeap.viewModel;

import ru.unn.agile.leftistHeap.model.Pair;
import ru.unn.agile.leftistHeap.model.LeftistHeap;

public class ViewModel {
    private LeftistHeap leftistHeap;

    public String keyAdd;
    public String valueAdd;
    public String keyGetDel;
    public String valueGetDel;
    public String status;

    public ViewModel() {
        keyAdd = "0";
        valueAdd = "Zero";
        keyGetDel = "";
        valueGetDel = "";
        status = "OK";
        leftistHeap = new LeftistHeap();
    }

    public void addToLeftistHeap(String key, String value) {
        try {
            leftistHeap.add(new Pair(Integer.parseInt(key), value));
            status  = "New element is successfully added";
        }
        catch (IllegalArgumentException ex) {
            status = "Invalid input";
        }
        catch (Exception ex) {
            status = "Cant add new element";
        }
    }

    public void getMinFromLeftistHeap() {
        try {
            Pair min = leftistHeap.getMin();
            keyGetDel = String.valueOf(min.getKey());
            valueGetDel = min.getValue();
            status = "Min is successfully got";
        }
        catch (Exception ex) {
            keyGetDel = "";
            valueGetDel = "";
            status = "Cant get min from empty heap";
        }
    }

    public void deleteMinFromLeftistHeap() {
        try {
            Pair min = leftistHeap.getMin();
            leftistHeap.deleteMin();
            keyGetDel = String.valueOf(min.getKey());
            valueGetDel = min.getValue();
            status = "Min is successfully deleted";
        }
        catch (Exception ex)  {
            keyGetDel = "";
            valueGetDel = "";
            status = "Cant delete min from empty heap";
        }
    }
}

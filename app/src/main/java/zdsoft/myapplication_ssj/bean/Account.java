package zdsoft.myapplication_ssj.bean;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 2016/11/12.
 */
public class Account {
    String name;
    String balance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Account(String name, String balance) {
        this.name = name;
        this.balance = balance;
    }

    public Account() {
        super();
    }
}

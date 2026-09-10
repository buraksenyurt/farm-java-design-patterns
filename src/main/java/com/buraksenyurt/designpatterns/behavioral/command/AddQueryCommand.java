package com.buraksenyurt.designpatterns.behavioral.command;

public class AddQueryCommand implements Command {
    private String query;
    private String domain;
    private DataTransferService receiver;

    public AddQueryCommand(String query, String domain, DataTransferService receiver) {
        this.query = query;
        this.domain = domain;
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        System.out.println("AddQueryCommand çalıştırılıyor..." + query);
        receiver.addQuery(query, domain);
    }
}

package db;

class TransactionExceptions extends Exception{
    TransactionExceptions(String errorMessage){
        super(errorMessage);
    }
}

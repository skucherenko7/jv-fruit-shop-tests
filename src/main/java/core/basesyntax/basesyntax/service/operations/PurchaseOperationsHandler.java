package core.basesyntax.basesyntax.service.operations;

import core.basesyntax.basesyntax.model.Operations;
import core.basesyntax.basesyntax.service.strategy.OperationHandler;

public class PurchaseOperationsHandler implements OperationHandler {
    @Override
    public Operations getOperation() {
        return Operations.PURCHASE;
    }

    @Override
    public int applyOperation(int result, int quantity) {
        int newResult = result - quantity;
        if (newResult < 0) {
            throw new RuntimeException("Balance after purchase is negative");
        }

        return newResult;
    }
}

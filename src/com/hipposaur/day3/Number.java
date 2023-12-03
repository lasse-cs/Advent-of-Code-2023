package com.hipposaur.day3;

public record Number(int value, int row, int columnStart, int columnEnd) {
    
    public boolean isAdjacentToSymbol(Symbol symbol) {
        if (Math.abs(row - symbol.row()) > 1)
            return false;
        
        // must be in columnStart - 1 to columnEnd + 1
        if (symbol.column() < columnStart - 1 || symbol.column() > columnEnd + 1)
            return false;

        return true;
    }

}

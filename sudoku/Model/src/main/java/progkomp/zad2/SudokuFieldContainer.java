package progkomp.zad2;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class SudokuFieldContainer implements Cloneable, Serializable {
    SudokuField[] container;
    int N;

    public SudokuFieldContainer(int N, final SudokuField[] fields) {
        this.N = N;
        container = new SudokuField[N];
        for (int i = 0; i < N; i++) {
            container[i] = fields[i];
        }

    }

    public boolean verify() {
        boolean[] tab = new boolean[N];
        for (int i = 0; i < N; i++) {
            tab[i] = false;
        }
        for (int i = 0; i < N; i++) {
            if (container[i].getFieldValue() == 0) {
                return false;
            } else {
                tab[container[i].getFieldValue() - 1] = true;
            }

        }
        for (int i = 0; i < N; i++) {
            if (!tab[i]) {
                return false;
            }

        }
        return true;
    }

    @Override
    public String toString() {
        String result = "";
        int i;
        for (i = 0; i < N - 1; i++) {
            result += container[i].toString() + " ";
        }
        return result + container[i].toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(1157, 371).append(container).append(N).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        SudokuFieldContainer sudokuFieldContainer = (SudokuFieldContainer) obj;
        return new EqualsBuilder().append(container, sudokuFieldContainer.container).append(N, sudokuFieldContainer.N)
                .isEquals();
    }
    
    public SudokuFieldContainer getClone() { 
        try { 
            return (SudokuFieldContainer) super.clone(); 
        } catch (CloneNotSupportedException e) {
            System.out.println(" Cloning not allowed. "); 
            return this; 
        } 
        
    }
}

package progkomp.zad2;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;

public class SudokuField implements Cloneable, Serializable, Comparable<SudokuField> {

    private IntegerProperty value;

    public SudokuField(int value) {
        this.value = new SimpleIntegerProperty(value);
        // this.value = value;
    }

    public IntegerProperty getField() {
        return value;
    }

    public int getFieldValue() {
        return value.get();
    }

    public void setFieldValue(int value) {
        this.value.set(value);
    }

    @Override
    public String toString() {
        return Integer.toString(this.value.get());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(193, 8801).append(value.get()).toHashCode();
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
        SudokuField sudokuField = (SudokuField) obj;
        return new EqualsBuilder().append(value.get(), sudokuField.value.get()).isEquals();
    }

    public int compareTo(final SudokuField obj) {
        return this.getFieldValue() - obj.getFieldValue();

    }

   public SudokuField clone() { 
       //try { 
       try {
           return (SudokuField) super.clone();
       } catch (CloneNotSupportedException e) {
// TODO Auto-generated catch block
           e.printStackTrace();
           return null;
       } 
       //}
       
   }

    
}

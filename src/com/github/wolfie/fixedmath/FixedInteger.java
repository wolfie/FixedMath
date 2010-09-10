package com.github.wolfie.fixedmath;

public class FixedInteger extends Number implements Comparable<FixedInteger> {
  private static final long serialVersionUID = -1698468498901298848L;
  
  public static FixedInteger ZERO = new FixedInteger();
  public static FixedInteger ONE = new FixedInteger(1, 0);
  
  private long value = 0;
  private int decimalLength = 0;
  
  private double decimalMultiplier;
  
  private FixedInteger() {
  }
  
  public FixedInteger(final int integer, final int decimal) {
    if (decimal < 0) {
      throw new IllegalArgumentException("decimal part may not be negative");
    }
    
    if (decimal != 0) {
      decimalLength = Integer.valueOf(decimal).toString()
          .length();
    }
    decimalMultiplier = Math.pow(10, decimalLength);
    value = (long) (integer * decimalMultiplier + decimal);
  }
  
  @Override
  public int intValue() {
    return (int) (value / decimalMultiplier);
  }
  
  @Override
  public long longValue() {
    return (long) (value / decimalMultiplier);
  }
  
  @Override
  public float floatValue() {
    return (float) (value / decimalMultiplier);
  }
  
  @Override
  public double doubleValue() {
    return value / decimalMultiplier;
  }
  
  @Override
  public int compareTo(final FixedInteger o) {
    // TODO
    throw new UnsupportedOperationException("not implemented yet");
  }
  
  public FixedInteger add(final FixedInteger other) {
    final FixedInteger newNumber = new FixedInteger();
    if (decimalLength == other.decimalLength) {
      newNumber.value = value + other.value;
      newNumber.decimalMultiplier = decimalMultiplier;
      newNumber.decimalLength = decimalLength;
      return newNumber;
    } else {
      final double largerMultiplier = Math.max(decimalMultiplier,
          other.decimalMultiplier);
      final double smallerMultiplier = Math.min(decimalMultiplier,
          other.decimalMultiplier);
      
      // this multiplier will always be >1
      final double usedMultiplier = largerMultiplier / smallerMultiplier;
      
      final long smallerValue = Math.min(value, other.value);
      final long largerValue = Math.max(value, other.value);
      newNumber.value = (long) (smallerValue * usedMultiplier + largerValue);
      
      newNumber.decimalLength = Math.max(decimalLength, other.decimalLength);
      newNumber.decimalMultiplier = largerMultiplier;
      
      return newNumber;
    }
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
      return true;
    } else if (obj == null) {
      return false;
    } else if (!(obj instanceof FixedInteger)) {
      return false;
    } else {
      final FixedInteger cObj = (FixedInteger) obj;
      if (decimalLength == cObj.decimalLength) {
        return value == cObj.value;
      }
      
    }
  }
}

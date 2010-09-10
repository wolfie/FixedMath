package com.github.wolfie.fixedmath;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FixedIntegerTest {
  @Test
  public void testAdd() {
    assertEquals(FixedInteger.ONE, FixedInteger.ZERO.add(FixedInteger.ONE));
  }
}

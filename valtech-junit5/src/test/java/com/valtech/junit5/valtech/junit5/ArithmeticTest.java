package com.valtech.junit5.valtech.junit5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;

public class ArithmeticTest {

	private Arithmetic arith;
	private static int zero;

	@org.junit.jupiter.api.Nested
	@DisplayName("this is for testing sub method of the arithmetic")
	public class SubTest {

		private Arithmetic arithmetic;

		@ParameterizedTest
		@CsvSource({ "'Subtracting 2 positive Nos',2,3,-1", "'Subtracting 2 negative Nos',-2,-3,1",
				"'Subtracting 1 negative 1 positive Nos',-2,3,-5" })
		public void testSub(String name, int a, int b, int res) {
			arithmetic = new ArithmeticImpl();
			assertEquals(res, arithmetic.sub(a, b));
		}

	}
	
	@org.junit.jupiter.api.Nested
    public class DivisionTest {
        
        private Arithmetic arithmetic;
        
        @Test
        public void testDivByZero(){
           arithmetic= new ArithmeticImpl();
           assertThrows(ArithmeticException.class,() -> { arithmetic.divd(2, zero);});
        }
    }

	@ParameterizedTest
	@CsvSource({ "'Adding 2 positive Nos', 2,3,5", "'Adding 2 negative Nos', -2,-3,-5",
			"'Adding 1 positive and 1 negative Nos', -2,3,1" })
	public void testAdd(String name, int a, int b, int res) {
		assertEquals(res, arith.add(a, b));
	}

	public static Stream<Arguments> argumentsForAdd() {
		return Stream.of(Arguments.of(2, 3, 5), Arguments.of(1, 3, 4));
	}

	@ParameterizedTest
	@MethodSource("argumentsForAdd")
	public void testAdd(int a, int b, int res) {
		assertEquals(res, arith.add(a, b));
	}

	@Test
	@DisplayName(value = "Generic test case for Add()")
	public void testAdd() {
		assertEquals(5, arith.add(2, 3));
		assertEquals(5, arith.add(3, 2));
	}

	@ParameterizedTest
	@ValueSource(ints = { 2, 3, 4, -1, -2 })
	@DisplayName(value = "{index} Testing for add")
	public void testAddZero(int a) {
		assertEquals(a, arith.add(0, a));
	}

	@BeforeAll
	public static void executeFirst() {
		System.out.println("Before All....Executed first!...");
		zero = 0;
	}

	@BeforeEach
	public void runBefore() {
		arith = new ArithmeticImpl();
		System.out.println("Run before....");
	}

	@Test
	public void testHi() {
		System.out.println("Hi");
	}

	@Test
	public void testHello() {
		System.out.println("Hello");
	}

	@AfterEach
	public void afterTest() {
		System.out.println("After each method: " + zero);
		// zero++;
	}

	@AfterAll
	public static void executeLast() {
		System.out.println(zero + " After all");
	}

}

package com.omernaci;

import java.math.BigDecimal;
import java.util.Arrays;

public class RecordSample {

    public static class Employee {
        private Long id;
        private String department;
        private BigDecimal salary;

        public Employee(Long id, String department, BigDecimal salary) {
            this.id = id;
            this.department = department;
            this.salary = salary;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public BigDecimal getSalary() {
            return salary;
        }

        public void setSalary(BigDecimal salary) {
            this.salary = salary;
        }
    }

    public record EmployeeRecord(Long id, String department, BigDecimal salary) {
    }

    public static void main(String[] args) {

        Employee employee = new Employee(1L, "Data Science", BigDecimal.ZERO);

        EmployeeRecord employeeRecord = new EmployeeRecord(2L, "IT", BigDecimal.ZERO);
        
        Arrays.stream(EmployeeRecord.class.getRecordComponents()).forEach(System.out::println);

    }

}

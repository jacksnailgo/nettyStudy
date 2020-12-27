package precidate;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * lambda表达式的初级应用
 */
public class TestMain {
    /**
     * 使用实现类的方式过滤
     */
    @Test
    public void testAge(){
        List<Employee> emps = DataUtils.getEmployees();
        List<Employee> result = queryEmployees(emps, new AgePredicateImpl());
        System.out.println(result);
    }

    /**
     * 采用匿名内部类的方式过滤
     */
    @Test
    public void testDouble(){
        List<Employee> emps = DataUtils.getEmployees();
        List<Employee> result = queryEmployees(emps, new Predicate() {
            @Override
            public boolean match(Employee employee) {
               return employee.getAge() < 25 && employee.getSalary() < 8000;
            }
        });
        System.out.println(result);
    }

    /**
     * 使用Lambda表达式
     */
    @Test
    public void test3(){
        List<Employee> emps = DataUtils.getEmployees();
        List<Employee> employees = queryEmployees(emps, employee -> employee.getSalary() > 10000);
        System.out.println(employees);
    }

    /**
     * 更简洁的lamda表达式
     */
    @Test
    public void test4(){
        List<Employee> emps = DataUtils.getEmployees();
        List<Employee> collect = emps.stream().filter(employee -> employee.getSalary() > 10000 && employee.getAge() > 30).collect(Collectors.toList());
        System.out.println(collect);
    }



    public List<Employee> queryEmployees(List<Employee> emps, Predicate employeePredicate) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp: emps) {
            if (employeePredicate.match(emp)) {
                result.add(emp);
            }
        }
        return result;
    }



}

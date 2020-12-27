package precidate;

public interface Predicate {

    public boolean match(Employee employee);
}

class SalaryPredicateImpl implements Predicate{

    @Override
    public boolean match(Employee employee) {
       return employee.getSalary() > 10000;
    }

}


class AgePredicateImpl implements Predicate{

    @Override
    public boolean match(Employee employee) {
        return employee.getAge() > 30;
    }

}


class AgeAndSalaryPredicateImpl implements Predicate{
    @Override
    public boolean match(Employee employee) {
        return employee.getAge() > 30 && employee.getSalary() >10000;
    }
}
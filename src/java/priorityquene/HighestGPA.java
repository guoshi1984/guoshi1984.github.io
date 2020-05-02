import java.util.*;
public class HighestGPA {
	public static class Student {
    		public String name;
    		public double cgpa;

    		// A parameterized student constructor
    		public Student(String name, double cgpa) {
			 this.name = name;
        		this.cgpa = cgpa;
    		}

    		public String getName() {
        		return name;
    		}

		@Override 
		public String toString() {
			return name;
		}
	}

	public static class StudentComparator implements Comparator<Student>{

            // Overriding compare()method of Comparator
                        // for descending order of cgpa
            public int compare(Student s1, Student s2) {
                if (s1.cgpa < s2.cgpa)
                    return 1;
                else if (s1.cgpa > s2.cgpa)
                    return -1;
                                return 0;
                }
        }

	public static void main(String[] args){
        	// Creating Priority queue constructor having
        	// initial capacity=5 and a StudentComparator instance
        	// as its parameters
        	PriorityQueue<Student> pq = new
             		PriorityQueue<Student>(2, new StudentComparator());

                Student student1 = new Student("C", 3);
                Student student2 = new Student("D", 2);
                Student student3 = new Student("E", 4);
                Student student4 = new Student("A", 5);
                Student student5 = new Student("B", 4);
                pq.add(student1);
                pq.add(student2);
                pq.add(student3);
                pq.add(student4);
                pq.add(student5);

                // Printing names of students in priority order,poll()
                // method is used to access the head element of queue
               	System.out.println("Priority Queue: " + pq);
	       	System.out.println("Students served in their priority order");

                while (!pq.isEmpty()) {
                	System.out.println(pq.poll().getName());
        	}
    }
}

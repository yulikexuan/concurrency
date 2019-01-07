# concurrency
Side project for Java Concurrency

## Fundamentals

### Thread Safety

- If multiple threads access the same mutable state variable
    - Don't share the state variable across threads 
    - Use synchronization whenever accessing the state variable 
    - Make the state variable immutable
    
- It is far easier to design a class to be threadͲsafe than to retrofit it for 
  thread safety later 
  
- The less code that has access to a particular variable, the easier it is to 
  ensure that all of it uses the proper synchronization, and the easier it is 
  to reason about the conditions under which a given variable might be accessed
  
- Correctness means that a class conforms to its specification

- A class is thread safe when it continues to behave correctly when accessed 
  from multiple threads 
  
- A class is thread safe if it behaves correctly when accessed from multiple 
  threads, regardless of the scheduling or interleaving of the execution of 
  those threads by the runtime environment, and with no additional 
  synchronization or other coordination on the part of the calling code 
  
- Stateless: Having no fields and references no fields from other classes

- The java.util.concurrent.atomic package contains atomic variable classes for 
  effecting atomic state transitions on numbers and object references
  
- Wherepractical,useexistingthread safeobjects,like AtomicLong ,tomanage 
  the state of a class
    - It is simpler to reason about the possible states and state transitions for 
      existing thread safe objects than it is for arbitrary state variables, and 
      this makes it easier to maintain and verify thread safety
      
- We were able to add one state variable to our servlet while maintaining thread 
  safety by using a thread safe object to manage the entire state of the servlet
  But if we want to add more state to our servlet, can we just add more thread 
  safe state variables? 
    - To preserve state consistency, update related state variables in a single 
      atomic operation 
      
 - Intrinsic Locks 
     - belonging to or part of the real nature of something or someone
 
 - Intrinsic Locks 
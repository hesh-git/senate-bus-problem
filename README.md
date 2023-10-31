# senate-bus-problem
Solution for Senate Bus Problem in Concurrent Programming

This problem was initially based on the Senate bus at Wellesley College. Riders come to a bus 
stop and wait for a bus. When the bus arrives, all the waiting riders invoke boardBus, but anyone who 
arrives while the bus is boarding has to wait for the next bus. The capacity of the bus is 50 people; if there 
are more than 50 people waiting, some will have to wait for the next bus. When all the waiting riders have 
boarded, the bus can invoke depart. If the bus arrives when there are no riders, it should depart 
immediately.

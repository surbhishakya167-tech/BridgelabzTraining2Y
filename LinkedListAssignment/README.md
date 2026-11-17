# Linked List Assignments (Java)

Contains nine independent menu-driven Java programs:
1. StudentRecordManagement.java
2. MovieManagementSystem.java
3. TaskScheduler.java
4. InventoryManagement.java
5. LibraryManagement.java
6. RoundRobinScheduling.java
7. SocialMediaFriends.java
8. UndoRedoTextEditor.java
9. TicketReservation.java

## Run in IntelliJ IDEA
1. Open this folder as a project, or copy `src/linkedlist` into your existing project's `src` folder.
2. Ensure a JDK 17 or later is configured.
3. Open any class and click the green Run icon beside `main`.
4. Each file is a separate program with its own menu.

Notes:
- Positions are 1-based.
- Round-robin waiting/turnaround calculations assume all processes arrive at time 0. Priority is stored but does not affect round-robin order.
- Undo/redo stores complete text snapshots and keeps at most 10 states.
- Ticket booking time is generated automatically from the computer's local date/time.

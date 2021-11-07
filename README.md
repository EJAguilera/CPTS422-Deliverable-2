# 422-Project-Deliverable-2
Task items completed for CPTS 422 Project Deliverable 2, submitted by Elisha Aguilera SID #011745359 for WSUE CPTS 489.

### Asumptions
- Operators and operands and considered 'flat', as in, 'a + (b + c)' is considered 2 operators and 3 operands, not 2 operators and 4 operands.
- Operators are exclusively considered elements described in the Checkstyle `TokenType` as 'operators', which excludes loops and function declarations as operators.
- Comments include both single-line and end-of-block comments, edge case doesn't include a comment block that opens but never closes.
- JUnit refuses to run `ExpressionCheckTest.java` and `LoopingStatementCheckTest.java` as coverage test, and thereby the `ExpressionCheck.java` and `LoopingStatementCheck.java` classes are not covered, despite the test-cases existing for them.
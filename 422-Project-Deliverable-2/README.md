# 422-Project-Deliverable-1
Task items completed for CPTS 422 Project Deliverable 1, submitted by Elisha Aguilera SID #011745359 for WSUE CPTS 489.

### Asumptions
- Operators and operands and considered 'flat', as in, 'a + (b + c)' is considered 2 operators and 3 operands, not 2 operators and 4 operands.
- Operators are exclusively considered elements described in the Checkstyle `TokenType` as 'operators', which excludes loops and function declarations as operators.
- Number of operands depends on the number of operators: most operators will always have at least two operands, with a limit subset having only a single operand.
- Comments include both single-line and end-of-block comments, edge case doesn't include a comment block that opens but never closes.
- Project is configured as a Maven project, but additional files installed by dependency managers are not included in this remote respository. All packages should be specified in the `pom.xml` file.
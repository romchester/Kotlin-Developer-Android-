# Kotlin-Developer-Android-
Solutions for test tasks for the position of a Kotlin (Android) developer intern.

# Task 1: Implement the getScore 
Explanation
- Edge Case Handling :
  - If the provided offset is less than the first stamp's offset, return the score from the first stamp.
  - If the provided offset is greater than or equal to the last stamp's offset, return the score from the last stamp.
- Binary Search :
  - Initialize two pointers, left and right, to the start and end of the array respectively.
  - Calculate the middle index mid.
  - If the offset at mid matches the provided offset, return the corresponding score.
  - If the offset at mid is less than the provided offset, move the left pointer to mid + 1.
  - If the offset at mid is greater than the provided offset, move the right pointer to mid - 1.
  - After exiting the loop, the right pointer will point to the largest index where the offset is less than or equal to the provided offset.

# Task 2: Write Unit Tests for getScore 
Test Cases:
- Offset Less Than First Stamp :
  - If the offset is less than the first stamp's offset, the function should return the initial score (0, 0).
  - Offset Greater Than or Equal to Last Stamp :
  - If the offset is greater than or equal to the last stamp's offset, the function should return the last score.
- Exact Match of Offset with One Stamp :
  - If the offset exactly matches one of the stamps, the function should return the corresponding score.
- Offset Between Two Stamps :
  - If the offset is between two stamps, the function should return the score of the last stamp that is less than or equal to the given offset.
- Random Offset Within Range :
  - Generate a random offset within the range of all stamps and check that the correct score is returned.
- Empty Stamps Array :
  - If the stamps array is empty, the function should either throw an exception or return the initial score (0, 0) (depending on requirements).

Explanation
- generateTestStamps() :
  - Generates an array of 100 random Stamp objects for testing purposes.
- Test Methods :
  - test getScore when offset is less than first stamp : Ensures that if the provided offset is less than the first stamp's offset, the initial score (0, 0) is returned.
  - test getScore when offset is greater than or equal to last stamp : Ensures that if the provided offset is greater than or equal to the last stamp's offset, the last score is returned.
  - test getScore when offset matches exactly one stamp : Ensures that if the provided offset matches an exact stamp's offset, the corresponding score is returned.
  - test getScore when offset is between two stamps : Ensures that if the provided offset is between two stamps, the score of the earlier stamp is returned.
  - test getScore with random offset within range : Generates a random offset within the range of all stamps and checks that the correct score is returned.
  - test getScore with empty stamps array : Ensures that if the stamps array is empty, the initial score (0, 0) is returned.

Feature: Drag and Drop - Negative Scenarios

  Background:
    Given User opens the application

  Scenario: Drag element without dropping it
    When User drags the element but does not release
    Then Element should return to original position
    And Drop target should show "Drop here" text

  Scenario: Drop outside the target area
    When User drags element and drops outside target zone
    Then Drop operation should fail
    And Element should revert to original location
    And Target should still show default text

  Scenario: Attempt to drag disabled element
    When User attempts to drag disabled drag element
    Then Drag operation should not succeed
    And Error message should display "Element is disabled"

  Scenario: Drop on invalid drop target
    When User drags element on invalid drop zone
    Then Drop should fail gracefully
    And Element should return to starting position

  Scenario: Rapid consecutive drag and drop operations
    When User performs drag and drop operation 3 times consecutively
    Then All 3 operations should succeed
    And Final drop text should be "Dropped!"

  Scenario: Drag element without target available
    When Target drop zone is hidden
    And User attempts to drag element
    Then Error should occur with message "Target not found"

  Scenario: Drag from empty source area
    When Source element is removed
    And User attempts to perform drag operation
    Then Exception should be thrown "Element not found"

  Scenario: Verify original element state after cancelled drag
    When User starts drag operation
    And User cancels the drag by pressing Escape key
    Then Element should remain in original position
    And Element CSS position should not change

  Scenario: Drop on same source element
    When User drags element and drops on itself
    Then Drop should fail or be rejected
    And Element should stay at original location

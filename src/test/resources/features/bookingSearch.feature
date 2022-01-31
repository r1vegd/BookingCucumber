Feature: Booking.com search
  Scenario: Search booking.com page for "Renaissance Minsk Hotel by Marriott"
    Given hotel name "Renaissance Minsk Hotel by Marriott"
    When user does search
    Then "Renaissance Minsk Hotel by Marriott" is the first search result on the page
    And rating should be "8.7"


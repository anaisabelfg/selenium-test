Feature: Games A-Z

  Scenario: Login successfully in the lobby
    Given The user is at 'games.williamhill.com'
    When opens the login dialogue
    And enters username
    And enters password
    And clicks Login
    Then user's profile is loaded

  Scenario: Load A-Z games list
    Given User is logged into the lobby
    When click in the Tab 'A-Z'
    Then shows the list of games

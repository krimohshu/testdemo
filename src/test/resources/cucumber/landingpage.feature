@landingpage

Feature: Testing landing Home Page functionality

  Scenario Outline: First Page of Search Result should have valid
    Given user navigate to "<navigateToPage>" page
    When User provide search "<searchText>" field
    Then verify result "<result>" of search criteria
    Examples: provided valid and invalid input
      | navigateToPage | searchText | result                                      |
      | PD_HOMEPAGE    | seel       | Pokédex_number,Height,Weight,TypeHeld_Items |


@landingpage

Feature: Testing landing Home Page functionality

  Scenario Outline: First Page of Search Result should have valid
    Given user navigate to "<navigateToPage>" page
    When User provide search "<searchText>" field
    Then verify result of search criteria
      | Pokédex_number | Height | Weight | Type  | Held_Items   |
      | 86             | 11     | 900    | water | aspear-berry |
    Examples: provided valid and invalid input
      | navigateToPage | searchText |
      | PD_HOMEPAGE    | seel       |


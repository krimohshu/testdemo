@landingpage

Feature: Testing landing Home Page functionality

  @happypath @validsearch
  Scenario Outline: First Page of Search Result should have valid
    Given user navigate to "<navigateToPage>" page
    When User provide search "<searchText>" field
    Then verify result of search criteria
      | Pokédex_number | Height | Weight | Type  | Held_Items   |
      | 86             | 11     | 900    | water | aspear-berry |
    Examples: provided valid and invalid input
      | navigateToPage | searchText |
      | PD_HOMEPAGE    | seel       |

  @not_so_happy_path @no_pokeman_search
  Scenario Outline: Verify "<error>" error for "<searchText>" No Pokémon found serach criteria
    Given user navigate to "<navigateToPage>" page
    When User provide search "<searchText>" field
    Then User should get the "<error>" message
    Examples: provided valid and invalid input
      | navigateToPage | searchText            | error             |
      | PD_HOMEPAGE    | INVALIDSEARCHCRITERIA | No Pokémon found! |

  @not_so_happy_path @invalid_search
  Scenario Outline: Verify "<error>" error for "<searchText>" Invalid search term serach criteria
    Given user navigate to "<navigateToPage>" page
    When User provide search "<searchText>" field
    Then User should get the "<error>" message
    Examples: provided valid and invalid input
      | navigateToPage | searchText | error               |
      | PD_HOMEPAGE    | ___        | Invalid search term |

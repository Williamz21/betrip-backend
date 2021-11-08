Feature: Traveler Adding
  As a Developer
  I want to add Traveler through API
  So that It can be available to use.
  Background:
    Given The EndPoint "http://localhost:%d/api/v1/travelers" is available
  @traveler-adding
  Scenario: Add Traveler
    When A Traveler Request is sent with values "Pedro",1,"0999999","pedro@pedro","pedrito123","987654321","pedrito.jpg"
    Then A response with status 200 is received
    And A Traveler Resource with values "Pedro",1,"0999999","pedro@pedro","pedrito123","987654321","pedrito.jpg" is included in Response Body

  @post-duplicated
  Scenario:Add post with existing Title
    Given A Traveler Resource with values "Pedro",1,"0999999","pedro@pedro","pedrito123","987654321","pedrito.jpg" is already stored
    When A Traveler Request is sent with values "Pedro",1,"0999999","pedro@pedro","pedrito123","987654321","pedrito.jpg"
    Then A response with status 422 is received
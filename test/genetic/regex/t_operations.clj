(ns genetic.regex.t-operations
  (:require
    [clojure.test :refer :all]
    [midje.sweet :refer :all]
    [genetic.regex.utils :as utils]
    [genetic.regex.operations :as operations]))

(facts "about `random-char`"
  (fact "gives me a character"
    (operations/random-char #{\a}) => (some-checker \a \.)))

(facts "about `random-string`"
  (fact "gives me a string"
    (operations/random-string #{\a} 1) => (some-checker "a" ".")))

#_(facts "about `new-part`"
  (fact "gives me a string"
    (operations/new-part "") => string?))

(facts "about `mutate-add`"
  (fact "adds a new item with `new-part`"
    (operations/mutate-add "possible-chars" #{}) => (one-of string?)
    (provided
      (operations/new-part "possible-chars") => string?)))

(facts "about `mutate-delete`"
  (fact "removes an item"
    (operations/mutate-delete #{"regex-part" "regex-part2"}) => (one-of string?)))

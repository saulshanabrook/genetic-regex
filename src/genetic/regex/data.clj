(ns genetic.regex.data
  "Evolve a regex that matches one group and not another

  GP version of http://nbviewer.ipython.org/url/norvig.com/ipython/xkcd1313.ipynb")

(def data {
  :presedents [
    (clojure.string/split
      "washington adams jefferson jefferson madison
      madison monroe monroe adams jackson jackson van-buren harrison polk
      taylor pierce buchanan lincoln lincoln grant grant hayes garfield
      cleveland harrison cleveland mckinley mckinley roosevelt taft wilson
      wilson harding coolidge hoover roosevelt roosevelt roosevelt
      roosevelt truman eisenhower eisenhower kennedy johnson nixon nixon
      carter reagan reagan bush clinton clinton bush bush obama obama"
      #"\s+")
    (clojure.string/split
      "clinton jefferson adams pinckney pinckney clinton king adams jackson
      adams clay van-buren van-buren clay cass scott fremont breckinridge
      mcclellan seymour greeley tilden hancock blaine cleveland harrison
      bryan bryan parker bryan roosevelt hughes cox davis smith hoover
      landon wilkie dewey dewey stevenson stevenson nixon goldwater
      humphrey mcgovern ford carter mondale dukakis bush dole gore kerry
      mccain romney"
      #"\s+")]
  :nfl [
    (clojure.string/split
      "colts saints chargers 49ers seahawks patriots panthers broncos
      chiefs eagles bengals packers"
      #"\s+")
    (clojure.string/split
      "jets dolphins bills steelers ravens
      browns titans jaguars texans raiders cowboys giants redskins bears
      lions vikings falcons buccaneers cardinals rams"
      #"\s+")]
  :cities_drugs [
    (clojure.string/split
      "lipitor nexium plavix advair ablify seroquel singulair crestor actos
      epogen"
      #"\s+")
    (clojure.string/split
      "paris trinidad capetown riga zurich shanghai vancouver chicago
      adelaide auckland"
      #"\s+")]
  :starwars_trek [
    (clojure.string/split
      "The Phantom Menace / Attack of the Clones / Revenge of the Sith /
      A New Hope / The Empire Strikes Back / Return of the Jedi"
    #"[\s|//]+")
    (clojure.string/split
      "The Wrath of Khan / The Search for Spock / The Voyage Home /
      The Final Frontier / The Undiscovered Country / Generations / First Contact /
      Insurrection / Nemesis"
    #"[\s|//]+")]})

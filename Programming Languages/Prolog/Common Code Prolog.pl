%Set up our dish scores
dishScore(a , 5).
dishScore(b , 4).
dishScore(c , 3).
dishScore(d , 2).
dishScore(e , 1).

%set up our diners with prefrences
d1([a, b, c, d, e], S1).
d2([d, b, e, a, c], S2).
d3([c, d, a, e, b], S3).
d4([e, c, a, d, b], S4).

%set up our coefficients
coefficients([1, 0.8, 0.6, 0.4, 0.2]).

%Calculate the rank score of each dish
scoreRanking(Ranking, Score) :-
  coefficients(Coeff),
  scoreRankingLoop(Ranking, Coeff, Score).

%calculate the total score of each diner, and put them into a list
scoreRankingLoop([], [], 0).
scoreRankingLoop([Dish|Dishes], [C|Coeff], FinalScore) :-
  dishScore(Dish, DishScore),
  scoreRankingLoop(Dishes, Coeff, RemainingScore),
  MyScore is DishScore * C,
  FinalScore is RemainingScore + MyScore.

compareOnScore(Delta, Left, Right) :-
  scoreRanking(Left, LScore),
  scoreRanking(Right, RScore),
  compare(Delta, LScore, RScore).

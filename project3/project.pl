name_age(alice, 20).
name_age(bob, 30).
name_age(eve, 40).
name_age(mallory, 30). 

is_older(X, Y):-
    name_age(X,Z), 
    name_age(Y,W),
    (   Z>W->   true; false).

/*personalized_greeting:-
    write('Enter your name: '),
    nl, 
    read(X), 
    write('Hello '),
    write(X).
parent(pam,bob).
parent(tom,bob).
parent(tom,liz).
parent(bob,ann).
parent(bob,pat).
parent(pat,jim).
parent(bob,peter).
parent(peter,jim).
predecessor(X, Z) :- 
    parent(X, Z).
predecessor(X, Z) :- 
    parent(X,Y),
    predecessor(Y, Z).
f(0):-!.
f(1):-!.
f(A,B) :-
    A > 1,
    Ax is A - 1 ,
    Bx is A - 2, 
    f(Ax, C1),
    f(Ax, C2),
    B is C1+C2*/
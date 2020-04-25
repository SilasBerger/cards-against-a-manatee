# Data
TSV (tab-separated values) files for the original set of black and white cards, mostly based on a selection of cards distributed by [Cards Against Humanity](https://s3.amazonaws.com/cah/CAH_MainGame.pdf) under the [Creative Commons BY-NC-SA 2.0 License](https://creativecommons.org/licenses/by-nc-sa/2.0/). The contents of these cards are for entertainment purposes only. They are in no way, shape or form intended to be offensive.

## Format
The following data columns are used (files do not contain column headers):
- `id`: Continuous (but potentially sparse) unique card id, `integer`.
- `text`: Text displayed on the card, `string`.
- `pick`: Black cards only, indicates the number of white cards required to create a combination with this card, `string`.

## Examples
```

## Examples
```
$ head black_cards_original.tsv
1       Why can’t I sleep at night?     1
2       What’s that smell?      1
3       I got 99 problems but ______ ain’t one. 1
4       Maybe she’s born with it. Maybe it’s ______.    1
5       What’s the next Happy Meal® toy?        1
6       Here is the church Here is the steeple Open the doors And there is ______.      1
7       It’s a pity that kids these days are all getting involved with. 1
8       Today on Maury: “Help! My son is ______.        1
9      Alternative medicine is now embracing the curative powers of ______.    1
10      And the Academy Award for ______ goes to ______.        2

```
$ head white_cards_original.tsv
1       Being on fire.
2       Women in yogurt commercials.
3      An oversized lollipop.
4      An Oedipus complex.
5      A tiny horse.
6      Barack Obama.
25      Darth Vader.
28      Five-Dollar Footlongs.
30      Free samples.
31      Estrogen.
```

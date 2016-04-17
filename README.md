# Scrabble Word Finder

This program takes input from the user to get a list of letters that they have then does a search over every word given through "words.txt". For any given word, if the number of total letters the user has is less than the count of letters for a word, we discard that word. Otherwise, we look through the individual letters in the word to evaluate whether it is a word that could be spelled with our letters. It is done using a hashmap to calculate the frequency of letters in the word compared to a hashmap of the frequency of letters in our letter set - this is essentially to discard words with more of a letter than what we have. Words with letters we don't have are also discarded.

Once we've filtered all words that we can't spell with what letters we have, we begin by evaluating the point values for each letter in the rest of the words. For every one of these words, we see if there was a previously best word - if so, replace that word with the word we just evaluated. Eventually we've found the best word in the list.

# AZERTY Keyboard

The AZERTY keyboard translation boils down to this: every key on the AZERTY keyboard maps to equivalent keys on a QWERTY keyboard (with extra characters on some keys). Using a character -> character hashmap, I entered all key differences between these keyboards, where AZERTY keyboard characters were keys in the hashmap, and the associated values are the characters on the QWERTY keyboard. However, characters A-Za-z (minus the M characters), were mapped in two aligned strings. (A->Q, Z->W, etc).

Once mapped, for any input given to the program in AZERTY format, I replaced the characters in the input with their equivalent QWERTY characters and added it to a translation string. To handle the sticky 'H' key, for any contiguous repetition of 'H' characters (captilalized or lower case), I removed all but the first occurance of the character.

# ISO 8601 DateTime

I used the formats given from the website for this program. Each format boils down to this: there is a value for the day, the month and the year, as well as the time stamp and time zone offset. If there was any extra information, I stripped that out to only have the day, month, year and time, if that information was available. If there was a "PM" value added on the end of the input, before I stripped that out, I used it to convert hours less than 12 to the hour plus 12 
(04:00:00 -> 16:00:00).

Once that was done, the string that was left over was in the format of either "3/20/2016" or "3/20/2016 04:00:00".
If any component of the date time string were just one character in size, I padded the component with a '0' character.
Then I formatted that string to follow the ISO8601 format - YYYY-MM-DDTHH:MM:SS. I did this by passing all the information to one of three iso8601 methods that evaluated the information passed to it. If given year was not four characters long, find whether the day or month given were and swap that around. Similarly, if the month value was larger than 12 values, swap it with the day value.

Lastly, if the string given was already in some pseduo ISO8601 format, break it down and rebuild it to follow the standard.

# Rebuilding the Golden Gate Bridge using Legos:

First, I began by searching the information on measurements for the bridge. All of the assumptions I've made
about rebuilding the bridge using Legos is based on the information found on this
website: http://goldengatebridge.org/research/factsGGBDesign.php#maintower

I also looked at a list of all Lego blocks on http://shop.lego.com/en-US/Pick-A-Brick-ByTheme to find
bricks I would personally use for building a structure. The only two bricks I figured to use are the 2x8 brick and 
the 2x2x2 cylindrical brick. I did this to minimize the number of bricks needed to rebuild the bridge. I mean, hey, 
it was estimated that City of New York would have to pay an excess of $160 billion, so I'm sure that San Francisco
will want to save as much cash as possible.

The effective length, width and height of the 2x8 Lego brick is:<br/>
(L)1.6cm x (W)6.4cm x (H)1.1cm ~ (L)0.63in x (W)2.5in x (H)0.43in ~ (L)0.05ft x (W) 0.21ft x (H)0.4ft. 

For the 2x2x2 brick: (Rad)1.6cm x (Rad)1.6cm x (H)2.1cm ~ (Rad)0.63in x (Rad)0.63in x (H)0.83in

Sources for the measurements of these bricks are found here:<br/>
2x8: http://www.brickowl.com/catalog/lego-brick-2-x-8-3007-93888<br/>
2x2x2: http://www.brickowl.com/catalog/lego-brick-round-2-x-2-x-2-with-bottom-axle-holder-x-shape-orientation-30361<br/>

So here is my estimation using what I have:

The specific height of each tower of the bridge is unclear using the website, but it is stated
that the height above the water is 746 ft, which is what I will use for the total height of each tower.
The two legs on each tower are (L)33 ft x (W)54 ft x (H)746 ft. To construct one tower of the bridge will require:

2 * ((33ft / 0.05ft) * (54ft / 0.21ft) * (746 ft / 0.4ft)) ~ 2 * (660 x 257 x 1865) ~ 632,682,600 bricks.

For both towers, we would need 1,265,365,200 bricks.

However, each leg on the tower is connected to each other using a series of beams. Because I have no measurements
for these beams, I'm going to assume that we can rely on using the deck of the bridge as the connection between them.

The the length and width of the bridge is 8981ft x 90 ft. No depth for the deck was given, 
but one brick thick is not going to cut it. I will make the bridge 10 bricks thick. That will require:

(8981ft / 0.05ft) * (90ft / .21ft) * (10 * 0.4ft) ~ 179620 * 498 * 4 ~ 357,803,040 bricks.

The volume of each of the two main cables cables is found using the volume of a right cylinder (this is going to be one bad cable)
and is, roughly, (pi * (3.03ft / 2) ^ 2 * 7650ft) ~ 54074.66ft in volume.<br/><br/>
Using 2x8 lego bricks, thats a whopping 12,874,919 bricks for each cable, 25,749,838 bricks for both cables.

Lastly, are the 250 pairs of suspension cables. I will use the 2x2x2 brick for these, 
as the cables are less than three inches in diameter. The brick is 1.6cm x 1.6cm x 2.1cm ~ 0.63in x 0.63in x 0.83in. 

If spaced evenly apart, and assuming that the distance from one abutment to the closest tower is 1/8
the length of the suspension span minus the thickness of the towers; and the distance between the towers is 3/4
the same span minus the thickness of the towers, we can figure out the total number
of bricks for the suspension cables.

Thickness of the towers, t, is 54ft; length of the suspension span of the bridge, l, is 6450ft; height of the
tower from the deck of the bridge, h, is 500ft.

The slope from an abutment to the closest main tower is:<br/>sqrt(((l / 8 - t) ^ 2) + (h ^ 2)) ~ 903ft. 

The slope from the center of the bridge to one main tower (half the distance between the towers) is:<br/>
sqrt((3 / 8 * l - t) ^ 2 + (h ^ 2)) ~ 2417ft

The number of suspension cables between one abutment and the closest tower:<br/>2 * ((l / 8 - t) / 50 ft) ~ 30 cables 
(roughly 15 cables per lateral side of the bridge).

The number of suspension cables between the center of the bridge and the towers:<br/>2 * (3 * (l / 8) - t) / 50 ~ 94 cables
(rougly 47 cables per lateral side of the bridge).

All together, that's 248 cables, where two were lost from rounding numbers.

Each suspension cable is spaced 50 ft apart from one-another and has a diameter of roughly 2.7 in. However,
the diameter of the cable is less important than the height (imagining that brick connections are 3.3 times 
the tensile strength of steel), so I will use height as the parameter for the number of bricks needed. 
The height of any suspension cable of this bridge is a function of: f(x) = c^2 - (a*x)^2.<br/><br/>
If we use a couple JavaScript functions (of which can be found <a href="https://github.com/everette00/codefoo/blob/master/suspensionHeight.js">here</a>) 
to take the monotonous effort of calculating 124 cable heights, this is the process to find the number of bricks:

var ends = Math.round(numBricks(903, 30) / 0.07) * 2;<br/>
var middle = Math.round(numBricks(2417, 90) / 0.07) * 2;

"ends" and "middle" were multiplied by two for the two ends and the two center pieces. 
When evaluated, the number of bricks needed for the two ends is 774,000 bricks, and the middle would 
require 6,491,372 bricks. Together, the suspension cables call for 7,265,372 bricks.

All together, to build the Golden Gate Bridge, we would need 1,648,918,078 2x8 bricks and 7,265,372 2x2x2 cylinder bricks,
or 1,656,183,450 bricks all together. And that doesn't include the possibly millions more bricks needed for all of 
the cross-sectional beams under the deck or the support beams for the towers. Nor does it take into account some of 
the bricks lost from rounding measurements.

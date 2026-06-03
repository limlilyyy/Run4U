package com.example.run4u.ui

import androidx.annotation.DrawableRes
import com.example.run4u.R

data class Recipe(
    @DrawableRes val imageResourceId: Int,
    val name: String,
    val details: String
)

val recipes = listOf(
    Recipe(
        R.drawable.roast_chicken,
        "Roast Chicken",
        "423kcal\n"+
                "Step 1: Heat oven to 200C/180C fan/gas 6. Season the chicken, rub with ½ tbsp oil, then put in a large roasting tin with the garlic and roast for 25-30 mins.\n" +
                "Step 2: Meanwhile, blitz the herbs, anchovy, capers, lemon juice, and remaining oil with some seasoning in a food processor until finely chopped. Set aside.\n" +
                "Step 3: Once the chicken is cooked, remove the tin from the oven and squeeze the garlic out of their skins. Tip in the rice and use a wooden spoon to break it up, then add the spinach and lemon zest and toss. Return to the oven for 5 mins. Divide between bowls and dollop on the salsa verde."
    ),
    Recipe(
        R.drawable.spaghetti_bolognese,
        "Spaghetti Bolognese",
        "350kcal\n"+
                "Step 1: Heat oil in a pan and cook onions and garlic until soft.\n" +
                "Step 2: Add minced beef and cook until browned.\n" +
                "Step 3: Stir in tomato puree, chopped tomatoes, and herbs. Simmer for 20 mins.\n" +
                "Step 4: Serve with cooked spaghetti and grated Parmesan."
    ),
    Recipe(
        R.drawable.grilled_salmon,
        "Grilled Salmon",
        "300kcal\n"+
                "Step 1: Preheat grill to medium-high heat.\n" +
                "Step 2: Season salmon with salt, pepper, and olive oil.\n" +
                "Step 3: Grill for 6-8 mins per side, or until cooked through.\n" +
                "Step 4: Serve with lemon wedges and steamed vegetables."
    ),
    Recipe(
        R.drawable.vegan_curry,
        "Vegan Curry",
        "250kcal\n"+
                "Step 1: Heat oil in a pot, cook onions and garlic until soft.\n" +
                "Step 2: Add curry powder, chickpeas, and coconut milk.\n" +
                "Step 3: Simmer for 20 mins.\n" +
                "Step 4: Serve with rice and fresh cilantro."
    ),
    Recipe(
        R.drawable.pancakes,
        "Pancakes",
        "150kcal\n"+
                "Step 1: Mix flour, sugar, baking powder, and salt.\n" +
                "Step 2: Add milk, eggs, and melted butter. Mix until smooth.\n" +
                "Step 3: Heat a non-stick pan and pour batter to form pancakes.\n" +
                "Step 4: Cook until bubbles form, then flip and cook until golden brown."
    ),
    Recipe(
        R.drawable.caprese_salad,
        "Caprese Salad",
        "200kcal\n"+
                "Step 1: Slice tomatoes and mozzarella.\n" +
                "Step 2: Arrange on a plate, alternating tomato and mozzarella slices.\n" +
                "Step 3: Drizzle with olive oil and balsamic glaze.\n" +
                "Step 4: Sprinkle with fresh basil, salt, and pepper."
    ),
    Recipe(
        R.drawable.beef_tacos,
        "Beef Tacos",
        "290kcal\n"+
                "Step 1: Cook minced beef in a pan with taco seasoning.\n" +
                "Step 2: Warm taco shells.\n" +
                "Step 3: Fill shells with beef, lettuce, cheese, and salsa.\n" +
                "Step 4: Serve with sour cream and guacamole."
    ),
    Recipe(
        R.drawable.vegetable_stir_fry,
        "Vegetable Stir Fry",
        "180kcal\n"+
                "Step 1: Heat oil in a wok, add garlic and ginger.\n" +
                "Step 2: Add mixed vegetables and stir-fry for 5-7 mins.\n" +
                "Step 3: Stir in soy sauce and sesame oil.\n" +
                "Step 4: Serve with steamed rice or noodles."
    ),
    Recipe(
        R.drawable.chocolate_brownies,
        "Chocolate Brownies",
        "350kcal\n"+
                "Step 1: Melt butter and chocolate in a bowl over simmering water.\n" +
                "Step 2: Stir in sugar, eggs, and vanilla.\n" +
                "Step 3: Fold in flour and cocoa powder.\n" +
                "Step 4: Pour into a baking tin and bake at 180C for 25-30 mins."
    ),
    Recipe(
        R.drawable.caesar_salad,
        "Caesar Salad",
        "220kcal\n"+
                "Step 1: Toss romaine lettuce with Caesar dressing.\n" +
                "Step 2: Add croutons and Parmesan cheese.\n" +
                "Step 3: Top with grilled chicken slices if desired.\n" +
                "Step 4: Serve immediately."
    ),
    Recipe(
        R.drawable.pizza_margherita,
        "Pizza Margherita",
        "270kcal\n"+
                "Step 1: Preheat oven to 220C.\n" +
                "Step 2: Roll out pizza dough and spread with tomato sauce.\n" +
                "Step 3: Top with mozzarella slices and fresh basil.\n" +
                "Step 4: Bake for 10-12 mins until crust is golden."
    )
)


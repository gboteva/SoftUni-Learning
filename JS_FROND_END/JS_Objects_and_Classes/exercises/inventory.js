function inventory(input){
    class Hero{
        constructor(heroName, heroLevel, items){
            this.heroName = heroName;
            this.heroLevel = heroLevel;
            this.items = items;
        }
    }

    let heros = [];

    for (let heroInfo of input) {
        let tokens = heroInfo.split('\/');
        let name = tokens[0];
        let level = Number(tokens[1]);
        let items = tokens[2].trim().split(', ');
        let hero = new Hero(name, level, items);
        heros.push(hero);
    }

    let sorted = heros.sort((a, b) =>{
        return a.heroLevel - b.heroLevel;
    })

    for (const hero of sorted) {
        console.log(`Hero: ${hero.heroName}`);
        console.log(`level => ${hero.heroLevel}`);
        console.log(`items => ${hero.items.join(', ')}`);
    }
}

inventory([
    'Batman / 2 / Banana, Gun',
    'Superman / 18 / Sword',
    'Poppy / 28 / Sentinel, Antara'
    ]
    
    )
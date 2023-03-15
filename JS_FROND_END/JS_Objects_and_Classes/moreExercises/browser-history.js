function browserHistory(object, array){
    for (const line of array) {
        action = line.split(' ')[0];
        site = line.split(' ')[1];
    
        
        if (action === 'Open'){
            object["Open Tabs"].push(site);
            object["Browser Logs"].push(`${action} ${site}`);

        }else if (action === 'Close'){
            if (object["Open Tabs"].includes(site)){
                let index = object["Open Tabs"].indexOf(site);
                object["Open Tabs"].splice(index,1);
                object["Recently Closed"].push(site);
                object["Browser Logs"].push(`${action} ${site}`)
            }
        } else if (action === 'Clear'){
            object["Open Tabs"] = [];
            object["Recently Closed"] = [];
            object["Browser Logs"] = [];
        }
    }

    console.log(object["Browser Name"]);

    console.log(`Open Tabs: ${object["Open Tabs"].join(', ')}`);
    console.log(`Recently Closed: ${object["Recently Closed"].join(', ')}`);
    console.log(`Browser Logs: ${object["Browser Logs"].join(', ')}`);
}

browserHistory(
    {"Browser Name":"Mozilla Firefox",
    "Open Tabs":["YouTube"],
    "Recently Closed":["Gmail", "Dropbox"],
    "Browser Logs":["Open Gmail", "Close Gmail", "Open Dropbox", "Open YouTube", "Close Dropbox"]},
    ["Open Wikipedia", "Clear History and Cache", "Open Twitter"]
)
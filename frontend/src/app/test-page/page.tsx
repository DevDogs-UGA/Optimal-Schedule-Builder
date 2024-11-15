import { Card } from "../components/ui/Card";
import { Navbar } from "../components/Navbar";
import Basemap from "../../../public/images/Basemap.png"

export default function Test() {
    return (
        <div className="">
            <Navbar />
            <div className="grid grid-grow grid-rows-8 grid-cols-2 span items-center justify-center pt-10 text-center">
                <div className="">
                    <Card image="" 
                    text="Fluffy has left the server." heading="Where'd the dawg go?" />
                </div>
                <div className="">
                    <Card image="https://cdn.outsideonline.com/wp-content/uploads/2023/03/Funny_Dog_S.jpg" 
                    text="This is our dawg his name is Fluffy. Fluffy likes to play fetch. We like Fluffy." heading="This is our dawg" />
                </div>
                <div className="">
                    <Card image="https://cdn.outsideonline.com/wp-content/uploads/2023/03/Funny_Dog_S.jpg" 
                    text="This is our dawg his name is Fluffy. Fluffy likes to play fetch. We like Fluffy." heading="This is our dawg" />
                </div>
                <div className="">
                    <Card image="https://cdn.outsideonline.com/wp-content/uploads/2023/03/Funny_Dog_S.jpg" 
                    text="This is our dawg his name is Fluffy. Fluffy likes to play fetch. We like Fluffy." heading="This is our dawg" />
                </div>
                <div className="">
                    <Card image="https://cdn.outsideonline.com/wp-content/uploads/2023/03/Funny_Dog_S.jpg" 
                    text="This is our dawg his name is Fluffy. Fluffy likes to play fetch. We like Fluffy." heading="This is our dawg" />
                </div>
            </div>
        </div>
    );
}
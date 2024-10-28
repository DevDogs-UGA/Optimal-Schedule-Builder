import Basemap from "../images/Basemap.png";
import { Button } from "../components/ui/ButtonRounded";

export default function Home() {
    return (
        <div className="flex h-[calc(100vh-5rem)] flex-grow flex-col items-center justify-center pt-20 text-center">
            <div className="flex rounded-xl bg-[#F3EDED] ease-in-out sm:h-[10rem] sm:w-[20rem] md:h-[12rem] md:w-[25rem] lg:h-[36rem] lg:w-[73rem] items-center justify-center p-4">
                <div className="w-full h-full">
                    <img src={Basemap.src} className="w-full h-full rounded-xl object-cover m-auto" />
                </div>
            </div>
            <div className= "p-2">
                <Button text="distance: xx miles" />
                <Button text="time: xx minutes" />
            </div>
        </div>
    );
}
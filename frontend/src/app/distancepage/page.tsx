import Basemap from "../images/Basemap.png";
import { Button } from "../components/ui/ButtonRounded";

export default function Home() {
    return (
        <div className="flex h-[calc(100vh-5rem)] flex-grow flex-col items-center justify-center pt-20 text-center">
            <div className="flex items-center justify-center mb-12 rounded-none border border-[#F8E6EA] border-opacity-40 bg-[#F3EDED] bg-opacity-80 shadow-lg shadow-black/[0.03] backdrop-blur-[0.5rem] sm:top-6 sm:h-[4rem] sm:w-[36rem] sm:rounded-full md:h-[3rem] md:w-[45rem] lg:w-[42rem] xl:w-[80rem]">
                <div className="flex-nowrap items-center justify-around gap-6 text-[12px] font-bold sm:flex">
                    <button className="h-[2.5rem] w-[7rem] rounded-full bg-[#F3B0B0] transition duration-300 ease-in-out shadow-md shadow-black/[0.4] hover:bg-bulldog-red/50 md:px-4">
                        MONDAY
                    </button>
                    <button className="h-[2.5rem] w-[7rem] rounded-full bg-[#F3B0B0] transition duration-300 ease-in-out shadow-md shadow-black/[0.4] hover:bg-bulldog-red/50 md:px-4">
                        TUESDAY
                    </button>
                    <button className="h-[2.5rem] w-[7rem] rounded-full bg-[#F3B0B0] transition duration-300 ease-in-out shadow-md shadow-black/[0.4] hover:bg-bulldog-red/50 md:px-4">
                        WEDNESDAY
                    </button>
                    <button className="h-[2.5rem] w-[7rem] rounded-full bg-[#F3B0B0] transition duration-300 ease-in-out shadow-md shadow-black/[0.4] hover:bg-bulldog-red/50 md:px-4">
                        THURSDAY
                    </button>
                    <button className="h-[2.5rem] w-[7rem] rounded-full bg-[#F3B0B0] transition duration-300 ease-in-out shadow-md shadow-black/[0.4] hover:bg-bulldog-red/50 md:px-4">
                        FRIDAY
                    </button>
                </div>
            </div>
            <div className="flex rounded-xl bg-[#F3EDED] ease-in-out sm:h-[10rem] sm:w-[20rem] md:h-[12rem] md:w-[25rem] lg:h-[36rem] lg:w-[53rem] items-center justify-center p-4">
                <div className="w-full h-full">
                    <img src={Basemap.src} className="w-full h-full rounded-xl object-cover m-auto" />
                </div>
            </div>
            <div className= "flex justify-end w-full p-2 gap-4">
                <Button text="distance: xx miles" />
                <Button text="time: xx minutes" />
            </div>
        </div>
    );
}
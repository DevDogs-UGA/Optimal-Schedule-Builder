import Basemap from "../../../public/images/Basemap.png";
import { Button } from "../components/ui/ButtonRounded";
import { Navbar } from "../components/Navbar";

export default function Home() {
    return (
        <div className="min-h-screen">
            <Navbar />
            <div className="flex h-[calc(100vh-5rem)] flex-grow flex-col items-center justify-center pt-20 text-center">
                <div className="flex items-center justify-center mb-12 rounded-none border border-[#F8E6EA] border-opacity-40 bg-[#F3EDED] bg-opacity-80 shadow-lg shadow-black/[0.03] backdrop-blur-[0.5rem] sm:top-6 sm:h-[4rem] sm:w-[42rem] sm:rounded-full md:h-[3rem]">
                    <div className="flex-nowrap items-center justify-around space-x-6 space-y-2 sm:space-y-0 text-[12px] font-bold sm:flex">
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
                <div className="flex flex-col items-center sm:h-full w-full p-4">
                    <div className="flex items-center justify-center rounded-xl p-4 bg-[#F3EDED] ease-in-out h-[36rem] w-full md:w-[41rem] lg:w-[53rem] xl:w-[73rem]">
                        <div className="w-full h-full">
                            <img src={Basemap.src} className="w-full h-full rounded-xl object-cover m-auto" />
                        </div>
                    </div>
                    <div className= "flex w-full justify-end p-2 gap-4 lg:w-[53rem] xl:w-[73rem]">
                        <Button className="shadow-md shadow-black/[0.35]" text="distance: xx miles" />
                        <Button className="shadow-md shadow-black/[0.35]" text="time: xx minutes" />
                    </div>
                </div>
            </div>
        </div>
    );
}
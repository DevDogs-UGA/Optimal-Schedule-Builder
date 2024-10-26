"use client";
import React from "react";
import { type EmblaOptionsType } from "embla-carousel";
import useEmblaCarousel from "embla-carousel-react";
import "../../../styles/embla.css";

type PropType = {
  slides: React.ReactNode[]; // Changed from number[] to React.ReactNode[]
  options?: EmblaOptionsType;
};

const EmblaCarousel: React.FC<PropType> = ({ slides, options }) => {
  const [emblaRef] = useEmblaCarousel(options);

  return (
    <section className="embla">
      <div className="embla__viewport" ref={emblaRef}>
        <div className="embla__container">
          {slides.map((slideContent, index) => (
            <div className="embla__slide" key={index}>
              {slideContent}
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default EmblaCarousel;

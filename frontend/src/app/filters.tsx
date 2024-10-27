import React, { useState } from 'react';

const Dropdown = React.FC = () => {
    const [selectedOption, setSelectedOption] = useState<string>('');
  
    const handleChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setSelectedOption(event.target.value);
    };

    return (
        <div>
            <label htmlFor="dropdown">Term</label>
            <select id="dropdown" value={selectedOption} onChange={handleChange}>
                <option value="Spring 2025"></option>
                <option value="Summer 2025"></option>
            </select>
            {selectedOption && <p>You selected: {selectedOption}</p>}
        </div>
    );

};

export default function Filters() {
    return (
        <div className="section-container">
            <div className="header">
                <h1>Filters</h1>
            </div>
            <div className="course-status-container">
                <p>Course Status: </p>
                <div className="left-col-container">
                    <input type="checkbox" id="open" name="open" defaultChecked />
                    <label htmlFor="open">Open</label>
                    <input type="checkbox" id="waitlist" name="waitlist" defaultChecked />
                    <label htmlFor="waitlist">Waitlist</label>
                    <input type="checkbox" id="closed" name="closed" defaultChecked />
                    <label htmlFor="closed">Closed</label>
                </div>
            </div>
            <div className="middle-col-container">
                <div className="term-container">
                    <label htmlFor="dropdown">Term</label>
                    <select id="dropdown" value={selectedOption} onChange={handleChange}>
                        <option value="Spring 2025"></option>
                        <option value="Summer 2025"></option>
                    </select>
                    {selectedOption && <p>You selected: {selectedOption}</p>}
                </div>
                <div className="instruction-container"></div>
                <div className="campus-container"></div>
            </div>
            <div className="right-col-container">
                <div className="level-container"></div>
                <div className="credit-hours-container"></div>
                <div className="building-container"></div>
            </div>
        </div>
    );
}
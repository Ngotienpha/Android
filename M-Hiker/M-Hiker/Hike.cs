using SQLite;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace M_Hiker
{
    [Table("Hike")]
    public class Hike
    {
        [PrimaryKey, AutoIncrement]
        public int HikeId { get; set; }
        public string HikeName { get; set;}
        public string HikeLocation { get; set;}
        public DateTime HikeDate { get; set; }
        public string HikeLength { get; set; }
        public string HikeLevel { get; set; }
        public string ParkingAvailable { get; set; }
        public Hike() { }

        public Hike(int hikeId, string hikeName, 
                    string hikeLocation, DateTime hikeDate, string hikeLength, string hikeLevel, string parkingAvailable)
        {
            HikeId = hikeId;
            HikeName = hikeName;
            HikeLocation = hikeLocation;
            HikeDate = hikeDate;
            HikeLength = hikeLength;
            HikeLevel = hikeLevel;
            ParkingAvailable = parkingAvailable;
        }
    }
}

package edu.turtlekit2.warbot.roknus;

public class dataExplorerRocket {
        private int id;
        private double angle;
        private double distance;
        
        public double getDistance() {
                return distance;
        }
        public void setDistance(double distance) {
                this.distance = distance;
        }
        public dataExplorerRocket(int id, double angle,double distance)
        {
                this.id = id;
                this.angle = angle;
                this.distance = distance;
        }
        public int getId() {
                return id;
        }
        public void setId(int id) {
                this.id = id;
        }
        public double getAngle() {
                return angle;
        }
        public void setAngle(double angle) {
                this.angle = angle;
        }
}
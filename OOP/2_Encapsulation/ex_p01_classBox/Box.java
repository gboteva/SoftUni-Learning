package box_enc;

public class Box {

        private double length;
        private double width;
        private double height;

        public Box(double length, double width, double height) {
            this.setLength(length);
            this.setWidth(width);
            this.setHeight(height);
        }

        private void checkForZeroOrNegativeValue(double value, String type) {
            if (value <= 0.0D) {
                throw new IllegalArgumentException(type + " cannot be zero or negative.");
            }
        }

        private void setLength(double length) {
            this.checkForZeroOrNegativeValue(length, "Length");
            this.length = length;
        }

        private void setWidth(double width) {
            this.checkForZeroOrNegativeValue(width, "Width");
            this.width = width;
        }

        private void setHeight(double height) {
            this.checkForZeroOrNegativeValue(height, "Height");
            this.height = height;
        }

        public double calculateSurfaceArea() {
            return 2.0D * (this.length * this.width + this.length * this.height + this.width * this.height);
        }

        public double calculateLateralSurfaceArea() {
            return 2.0D * this.length * this.height + 2.0D * this.width * this.height;
        }

        public double calculateVolume() {
            return this.length * this.width * this.height;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("Surface Area - %.2f", this.calculateSurfaceArea())).append(System.lineSeparator());
            builder.append(String.format("Lateral Surface Area - %.2f", this.calculateLateralSurfaceArea())).append(System.lineSeparator());
            builder.append(String.format("Volume – %.2f", this.calculateVolume()));
            return builder.toString().trim();
        }
}

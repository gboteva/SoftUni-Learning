package viceCity.models.guns;

public class Pistol extends BaseGun{

    private final static int CAPACITY = 10;
    private final static int TOTAL_BULLETS = 100;
    private final static int BULLETS_SHOOT_WITH_ONE_PUSH = 1;

    public Pistol(String name) {
        super(name, CAPACITY, TOTAL_BULLETS);
        setCanFire(canFire());
    }

    @Override
    public boolean canFire() {
        return super.getTotalBullets() >= BULLETS_SHOOT_WITH_ONE_PUSH
                || getBulletsPerBarrel() >= BULLETS_SHOOT_WITH_ONE_PUSH;
    }

    @Override
    public int fire() {
       if (getBulletsPerBarrel() <= 0){
            reloadBarrel();
       }

       if (getBulletsPerBarrel() > 0 || getBulletsPerBarrel() >= BULLETS_SHOOT_WITH_ONE_PUSH){

           setBulletsPerBarrel(getBulletsPerBarrel() - BULLETS_SHOOT_WITH_ONE_PUSH);

           return BULLETS_SHOOT_WITH_ONE_PUSH;
       }

       return 0;
    }

    private void reloadBarrel() {
        if (getTotalBullets() >= CAPACITY) {
            setBulletsPerBarrel(CAPACITY);
            setTotalBullets(getTotalBullets() - CAPACITY);
        } else if (getTotalBullets() > 0 && getTotalBullets() < CAPACITY) {
            setBulletsPerBarrel(getTotalBullets());
            setTotalBullets(0);
        } else {
            setCanFire(false);
        }
    }
}

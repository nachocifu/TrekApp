package domain;

/**
 * Review containing a rating, the profile of the user who sent it, and who is receiving 
 * it and an optional message that the sender can add to the review
 */

public class Review {

	//private enum Rating {veryBad, bad, regular, good, veryGood, excellent};
		private Integer rating;
		private Profile profileTarget;
		private Profile profileOrigin;
		private String comment;
		
		public Review(Profile profile_orgin, Profile profileTarget) {
			this.profileOrigin = profile_orgin;
			this.profileTarget = profileTarget;
		}

		/**
		 * @return the reviews rating
		 */
		public Integer getRating() {
			return rating;
		}
		
		/**
		 * @param rating
		 * @throws IllegalArgumentException
		 */
		public void setRating(Integer rating) throws IllegalArgumentException {
			if(rating >= 0 && rating <= 5){
				this.rating = rating;
			}else{
				throw new IllegalArgumentException();
			}
		}

		/**
		 * @param comment that will be added to the review
		 */
		public void setComm(String comment)
		{
			this.comment=comment;
		}
		
		/**
		 * @return the review's comment
		 */
		public String getComm()
		{
			return this.comment;
		}
		
		/**
		 * @return the user the review is targeted to
		 */
		public Profile getprofileTarget() {
			return profileTarget;
		}
		
		/**
		 * @return the user that is sending the review
		 */
		public Profile getprofileOrigin() {
			return profileOrigin;
		}
}

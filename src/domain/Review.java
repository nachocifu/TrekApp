package domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Review containing a rating, the profile of the user who sent it, and who is receiving
 * it and an optional message that the sender can add to the review
 */
@DatabaseTable
public class Review {

		@DatabaseField
		private Integer rating;


		private Profile profileTarget;

		private Profile profileOrigin;

		@DatabaseField
		private String comment;

		@DatabaseField(generatedId = true)
		private Integer id;

		public Review(){

		}

		/**
		 * New review only containig who created the review and who its destined for,
		 * in other word, an empty review(it doesnt contain a rating or a comment)
		 * @param profile_orgin
		 * @param profileTarget
		 */
		public Review(Profile profile_orgin, Profile profileTarget) {
			this.profileOrigin = profile_orgin;
			this.profileTarget = profileTarget;
		}

		/**
		 * @param rec reciever of the review, profile that is being reviewed
		 * @param send sender of the review, profile that is making the review
		 * @param msg description contained in the review, the senders opinion on the reciever
		 * @param rat rating, whole number from 0 to 5 representing the overall opinion on the reciever
		 */
		public Review(Profile rec, Profile send, String msg, Integer rat){
			setRating(rat);
			this.profileTarget = rec;
			this.profileOrigin = send;
			this.comment = msg;
		}

		/**
		 * @return the reviews rating
		 */
		public Integer getRating() {
			return rating;
		}

		/**
		 * @param rating
		 * @throws IllegalArgumentException when the rating being set is not a value between 0 and 5
		 */
		private void setRating(Integer rating) throws IllegalArgumentException {
			if(rating >= 0 && rating <= 5){
				this.rating = rating;
			}else{
				throw new IllegalArgumentException("The rating must be a number between 0 and 5");
			}
		}

		/**
		 * @return the review's comment
		 */
		public String getComment()
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

		public Integer getId() {
			return id;
		}
}

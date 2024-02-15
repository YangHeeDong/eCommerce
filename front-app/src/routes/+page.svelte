<script>

    let home = $state([]);
    import { onMount } from "svelte";

    onMount(() => {
        fetch('http://localhost:8010/api/v1/home')
            .then(response => response.text())
			.then(data => {
                home = data;
            });
    });

</script>

<svelte:head>
	<title>Home</title>
	<meta name="description" content="Svelte demo app" />
</svelte:head>

<section>
	{home}
</section>
